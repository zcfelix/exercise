require_relative '../lib/game/player.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/land.rb'
require_relative '../lib/tool/tool.rb'
require_relative '../lib/tool/block.rb'
require_relative '../lib/tool/bomb.rb'
require_relative '../lib/tool/robot.rb'
require_relative '../lib/game/config.rb'

describe Tool do
  before(:each) do
    @start_points = 2000
    @player = Player.new :points => @start_points
    @bomb = Bomb.new
  end

  it 'should palyer buy tool' do
    expect(@player.buy_tool(@bomb)).to eq(true)
    expect(@player.points).to eq(@start_points - @bomb.points)
    expect(@player.tools[@bomb]).to eq(1)
    expect(@player.tool_quantity).to eq(1)
  end

  it "should player not buy tool when tool quantity is max" do
    ToolConf::MAX_QUANTITY.times do
      expect(@player.buy_tool(@bomb)).to eq(true)
    end
    expect(@player.buy_tool(@bomb)).to eq(false)
    expect(@player.points).to eq(@start_points - @bomb.points * ToolConf::MAX_QUANTITY)
    expect(@player.tool_quantity).to eq(ToolConf::MAX_QUANTITY)
  end

  it "should player not buy tool when points is not enough" do
    player = Player.new :points => 0
    expect(player.buy_tool(@bomb)).to eq(false)
    expect(player.tool_quantity).to eq(0)
  end

end
