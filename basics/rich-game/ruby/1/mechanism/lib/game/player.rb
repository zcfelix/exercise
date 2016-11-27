require_relative 'config.rb'
class Player
  attr_reader :current_place, :balance, :lands, :tools, :tool_quantity, :points
  attr_accessor :status, :no_punish_turns, :paused_turns

  def initialize *args
    unless args.empty?
      hash = args[-1]
      hash.each do |k, v|
        instance_variable_set("@#{k}", v)
      end
    end
    @status ||= :wait_for_command
    @lands = []
    @tools = Hash.new(0)
    @tool_quantity = 0
    @no_punish_turns = 0
    @paused_turns = 0
  end

  def execute(command)
    @last_executed = command
    @status = command.execute(self)
    end_turn_work if @status == :end_turn
  end

  def respond(response)
    @status = @last_executed.execute_with(self, response)
    end_turn_work if @status == :end_turn
  end

  def move_to(target)
    @current_place = target
  end

  def buy_land(land)
    if (balance >= land.price && land.owner.nil?)
      @lands << land
      @balance -= land.price
      land.owner = self
      true
    else
      false
    end
  end

  def upgrade(land)
    if (@balance >= land.price && land.owner == self && land.level < LandConf::TOP_LEVEL)
      land.upgrade
      @balance -= land.price
      true
    else
      false
    end
  end

  def buy_tool(tool)
    if (@points >= tool.points && @tool_quantity < ToolConf::MAX_QUANTITY)
      @tools[tool] += 1
      @tool_quantity += 1
      @points -= tool.points
      true
    else
      false
    end
  end

  def pay(amount)
    @balance -= amount
  end

  def gain(amount)
    @balance += amount
  end

  def gain_points(amount)
    @points += amount
  end

  def can_be_punished?
    @no_punish_turns == 0
  end

  def can_gain_road_toll?
    true
  end

  def start_turn
    if need_wait?
      @status = :wait_for_turn
      @paused_turns -= 1
      :wait_for_turn
    else
      :wait_for_command
    end
  end

  private
  def end_turn_work
    @no_punish_turns -= 1 if @no_punish_turns > 0
  end

  def need_wait?
    @paused_turns > 0
  end

end
