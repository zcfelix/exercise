require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/mineral_estate.rb'
require_relative '../lib/game/config.rb'


describe RollCommand do
  before(:each) do
    @step = 1
    @points_in_mineral = 80
    @target = MineralEstate.new(@points_in_mineral)
    @map = Map.new
    @roll = RollCommand.new(@map, @step)
    allow(@map).to receive(:move) {@target}
    @player = Player.new :points => 0
    @player.execute(@roll)
  end

  it 'should player gain points and end_turn when rolling to mineral estate' do
    expect(@player.points).to eq(@points_in_mineral)
    expect(@player.status).to eq(:end_turn)
  end

end
