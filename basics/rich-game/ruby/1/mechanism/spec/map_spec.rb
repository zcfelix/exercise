require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/mineral_estate.rb'
require_relative '../lib/game/config.rb'


describe Map do

  before(:each) do
    @map = Map.new
    @player = Player.new
  end

  it 'should map be initialized' do
    puts @map.to_s
  end

end
